interface MoveValidator<Vector2d> {
    fun canMoveTo(position: Vector2d): Boolean
}