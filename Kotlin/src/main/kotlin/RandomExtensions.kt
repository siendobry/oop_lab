fun Map<Vector2d, Any>.randomPosition(): Vector2d? {
    return this.keys.randomOrNull()
}

fun Map<Vector2d, Any>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val freePositions = ArrayList<Vector2d>()
    for (i in 0 until mapSize.x) {
        for (j in 0 until mapSize.y) {
            if (!this.keys.contains(Vector2d(i, j))) {
                freePositions.add(Vector2d(i, j))
            }
        }
    }
    return freePositions.randomOrNull()
}