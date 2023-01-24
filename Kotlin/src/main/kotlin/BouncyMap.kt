import java.lang.IllegalArgumentException

class BouncyMap(
    private val height: Int,
    private val width: Int): IWorldMap {

    private val animals = HashMap<Vector2d, Animal>()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(this.height - 1, this.width - 1))
    }

    override fun objectAt(position: Vector2d): Any? = this.animals[position]

    override fun place(animal: Animal) {
        if (canMoveTo(animal.position)) {
            var finalPosition: Vector2d? = null
            if (!super.isOccupied(animal.position) || this.objectAt(animal.position) === animal) {
                finalPosition = animal.position
            } else if (this.animals.randomFreePosition(Vector2d(this.height, this.width)) != null) {
                finalPosition = this.animals.randomFreePosition(
                    Vector2d(
                        this.height,
                        this.width
                    )
                )!! // idk why do i have to use !! here
            } else if (this.animals.randomPosition() != null) {
                finalPosition = this.animals.randomPosition()!! // same thing here

            }
            if (finalPosition != null) {
                this.animals[finalPosition] = animal
                animal.updatePosition()
            }
        }
        else {
            throw IllegalArgumentException("An animal's position is outside of map boundaries!")
        }
    }

    override fun getPosition(entity: Any): Vector2d? {
        return this.animals.filterValues { it === entity }.keys.elementAtOrNull(0)
    }

    override fun toString(lowerLeft: Vector2d, upperRight: Vector2d): String {
        return MapVisualizer(this).draw(lowerLeft, upperRight)
    }

    override fun calcLowerLeft(): Vector2d {
        return Vector2d(0, 0)
    }

    override fun calcUpperRight(): Vector2d {
        return Vector2d(this.height - 1, this.width - 1)
    }

}