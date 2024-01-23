fun MapDirection.toUnitVector() = when (this) {
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.EAST -> Vector2d(1, 0)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.WEST -> Vector2d(-1, 0)
    else -> throw IllegalArgumentException("Unknown direction: $this")
}

data class Vector2d(val x: Int, val y: Int) {
    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)

    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)

    infix fun follows(other: Vector2d) = x >= other.x && y >= other.y

    infix fun precedes(other: Vector2d) = x <= other.x && y <= other.y

    fun upperRight(other: Vector2d) = Vector2d(x.coerceAtLeast(other.x), y.coerceAtLeast(other.y))

    fun lowerLeft(other: Vector2d) = Vector2d(x.coerceAtMost(other.x), y.coerceAtMost(other.y))

    fun opposite() = Vector2d(-x, -y)

    override fun toString() = "($x,$y)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector2d) return false

        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}