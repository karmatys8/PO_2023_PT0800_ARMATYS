data class MapDirection(val value: String) {
    companion object {
        val NORTH = MapDirection("^")
        val EAST = MapDirection(">")
        val SOUTH = MapDirection("v")
        val WEST = MapDirection("<")

        private val directions = listOf(NORTH, EAST, SOUTH, WEST)
    }

    override fun toString() = value

    fun next() = directions[(directions.indexOf(this) + 1) % directions.size]

    fun previous() = directions[(directions.indexOf(this) - 1 + directions.size) % directions.size]
}