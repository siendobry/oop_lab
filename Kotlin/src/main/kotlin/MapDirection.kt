enum class MapDirection {

    NORTH,
    SOUTH,
    WEST,
    EAST;

    override fun toString(): String {
        return when (this) {
            NORTH -> "N"
            SOUTH -> "S"
            WEST -> "W"
            EAST -> "E"
        }
    }

    fun next(): MapDirection {
        return when (this) {
            NORTH -> WEST
            SOUTH -> EAST
            WEST -> SOUTH
            EAST -> NORTH
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            NORTH -> WEST
            SOUTH -> EAST
            WEST -> SOUTH
            EAST -> NORTH
        }
    }

}