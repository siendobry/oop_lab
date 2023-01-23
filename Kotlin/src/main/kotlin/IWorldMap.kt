/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo (rewritten to Kotlin by siendobry)
 *
 */

interface IWorldMap {

    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    fun canMoveTo(position: Vector2d): Boolean

    /**
     * Place an animal on the map.
     *
     * @param animal
     *            The animal to place on the map.
     * @return Void (Unit)
     */
    fun place(animal: Animal)

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean = this.objectAt(position) != null

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    fun objectAt(position: Vector2d): Any?

    /**
     * Return a given object's position on the map
     *
     * @param entity
     *            The object which position is to be found
     * @return Object's position or null if object is not present on the map
     */
    fun getPosition(entity: Any): Vector2d?

    fun toString(lowerLeft: Vector2d, upperRight: Vector2d): String

    fun calcLowerLeft(): Vector2d

    fun calcUpperRight(): Vector2d

}