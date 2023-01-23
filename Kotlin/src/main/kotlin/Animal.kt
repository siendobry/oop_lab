data class Animal(
    private var orientation_: MapDirection,
    private var position_: Vector2d,
    private val map: IWorldMap) {

    constructor(): this(BouncyMap(5, 5))

    constructor(map: IWorldMap): this(Vector2d(2, 2), map)

    constructor(position: Vector2d, map: IWorldMap): this(MapDirection.NORTH, position, map)

    // getters
    val orientation: MapDirection get() = this.orientation_
    val position: Vector2d get() = this.position_

    override fun toString() = this.orientation_.toString()

    fun getDesc() = this.position_.toString()

    fun isAt(position: Vector2d): Boolean = this.position_ == position

    fun move(direction: MoveDirection) {
        when (direction) {
            MoveDirection.FORWARD -> if (this.map.canMoveTo(this.position_ + this.orientation_.toUnitVector())) {
                this.position_ = this.position_ + this.orientation_.toUnitVector()
            }
            MoveDirection.BACKWARD -> if (this.map.canMoveTo(this.position_ - this.orientation_.toUnitVector())) {
                this.position_ = this.position_ - this.orientation_.toUnitVector()
            }
            MoveDirection.RIGHT -> this.orientation_ = this.orientation_.next()
            MoveDirection.LEFT -> this.orientation_ = this.orientation_.previous()
        }
    }

    fun updatePosition() {
        val position: Vector2d? = this.map.getPosition(this)
        if (position != null) this.position_ = position
    }

}