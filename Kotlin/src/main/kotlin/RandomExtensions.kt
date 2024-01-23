fun <T> Map<Vector2d, T>.randomPosition(): Vector2d? {
    return if (isEmpty()) null else keys.random()
}
// both of those functions can return null
// that is why I don't use them in BouncyMap
fun <T> Map<Vector2d, T>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val allPositions = (0..<mapSize.x).flatMap { x ->
        (0..<mapSize.y).map { y -> Vector2d(x, y) }
    }

    val occupiedPositions = keys
    val freePositions = allPositions - occupiedPositions

    return if (freePositions.isNotEmpty()) {
        freePositions.random()
    } else {
        null
    }
}