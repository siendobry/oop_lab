data class Vector2d(
    private val x_: Int,
    private val y_: Int) {

    // getters
    val x: Int get() = x_
    val y: Int get() = y_

    override fun toString(): String {
        return "${this.x_}, ${this.y_}"
    }

    // It is impossible to overload comparing operators for follows() and precedes()
    // operations since they do not introduce total ordering of all possible Vector2ds

//    operator fun compareTo(other: Vector2d): Int {
//        if (this.x_ >= other.x_ && this.y_ >= other.y_) {
//            if (this.x_ > other.x_ && this.y_ > other.y_) {
//                return 1
//            }
//            return 0
//        }
//        else if (this.x_ <= other.x_ && this.y_ <= other.y_) {
//            if (this.x_ < other.x_ && this.y_ < other.y_) {
//                return -1
//            }
//            return 0
//        }
//    }

    fun precedes(other: Vector2d): Boolean = this.x <= other.x && this.y <= other.y

    fun follows(other: Vector2d): Boolean = this.x >= other.x && this.y >= other.y

    operator fun plus(other: Vector2d) = Vector2d(this.x_ + other.x_, this.y_ + other.y_)

    operator fun minus(other: Vector2d) = this + (-other)

    fun upperRight(other: Vector2d) = Vector2d(this.x_.coerceAtLeast(other.x_), this.y_.coerceAtLeast(other.y_))

    fun lowerLeft(other: Vector2d) = Vector2d(this.x_.coerceAtMost(other.x_), this.y_.coerceAtMost(other.y_))

    operator fun unaryMinus() = Vector2d(-this.x_, -this.y_)

}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
        MapDirection.EAST -> Vector2d(1, 0)
    }
}