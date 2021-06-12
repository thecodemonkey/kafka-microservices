package kafka.es.query.model

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

data class OrderEvent (
    var id: String? = null,

    var userId: String? = null,
    var productId: String? = null,
    var amount: Int? = null,
    var price: Int? = null
)

inline infix fun <reified T : Any> T.merge(other: T): T {
    val nameToProperty = T::class.declaredMemberProperties.associateBy { it.name }
    val primaryConstructor = T::class.primaryConstructor!!
    val args = primaryConstructor.parameters.associateWith { parameter ->
        val property = nameToProperty[parameter.name]!!
        val valueOther = property.get(other)
        val valueThis = property.get(this)

        if (valueOther is Double && valueOther == 0.0) {
            valueThis
        } else {
            valueOther ?: valueThis
        }
    }
    return primaryConstructor.callBy(args)
}
