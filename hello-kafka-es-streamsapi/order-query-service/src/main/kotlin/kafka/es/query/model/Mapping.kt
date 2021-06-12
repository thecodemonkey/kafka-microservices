package kafka.es.query.model

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor


fun List<OrderEvent>.toOrders() = this.map { it.toOrder() }

fun OrderEvent.toOrder() = Order(
    this.id!!,
    this.productId,
    this.userId,
    this.amount,
    this.price
)

