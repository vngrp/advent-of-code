fun String.regexGroups(input: String) = toRegex().findAll(input).toList().map { it.groupValues }
fun String.grab(regex: String) = regex.toRegex().findAll(this).map { it.groupValues.drop(1).first() to it.range }

fun List<String>.grab(regex: String): List<Pair<String, List<Point<String>>>> =
    mapIndexed { row, element ->
        element
            .grab(regex)
            .map { (group, range) ->
                group to range.map { column -> Point(column, row, group) }
            }
            .toList()
    }
        .flatten()

/**
 * Helpful data representations
 */
enum class Cardinal { NORTH, EAST, SOUTH, WEST }
enum class Direction { LEFT, RIGHT, UP, DOWN }
data class Point<T>(val x: Int, val y: Int, val value: T, val orientation: Cardinal = Cardinal.NORTH) {
    constructor(pair: Pair<Int, Int>, value: T) : this(pair.first, pair.second, value)

    fun move(cardinal: Cardinal, distance: Int) = when (cardinal) {
        Cardinal.NORTH -> Point(x, y - distance, orientation)
        Cardinal.SOUTH -> Point(x, y + distance, orientation)
        Cardinal.WEST -> Point(x - distance, y, orientation)
        Cardinal.EAST -> Point(x + distance, y, orientation)
    }

    fun move(direction: Direction, distance: Int) = when (direction) {
        Direction.UP -> Point(x, y - distance, orientation)
        Direction.DOWN -> Point(x, y + distance, orientation)
        Direction.LEFT -> Point(x - distance, y, orientation)
        Direction.RIGHT -> Point(x + distance, y, orientation)
    }

    fun move(orientation: Cardinal, direction: Direction, distance: Int) = when (orientation) {
        Cardinal.NORTH -> when (direction) {
            Direction.LEFT -> Point(x - distance, y, Cardinal.WEST)
            Direction.RIGHT -> Point(x + distance, y, Cardinal.EAST)
            Direction.UP -> Point(x, y - distance, Cardinal.NORTH)
            Direction.DOWN -> Point(x, y + distance, Cardinal.SOUTH)
        }
        Cardinal.SOUTH -> when (direction) {
            Direction.LEFT -> Point(x + distance, y, Cardinal.EAST)
            Direction.RIGHT -> Point(x - distance, y, Cardinal.WEST)
            Direction.UP -> Point(x, y + distance, Cardinal.SOUTH)
            Direction.DOWN -> Point(x, y - distance, Cardinal.NORTH)
        }
        Cardinal.WEST -> when (direction) {
            Direction.LEFT -> Point(x, y + distance, Cardinal.SOUTH)
            Direction.RIGHT -> Point(x, y - distance, Cardinal.NORTH)
            Direction.UP -> Point(x - distance, y, Cardinal.WEST)
            Direction.DOWN -> Point(x + distance, y, Cardinal.EAST)
        }
        Cardinal.EAST -> when (direction) {
            Direction.LEFT -> Point(x, y - distance, Cardinal.NORTH)
            Direction.RIGHT -> Point(x, y + distance, Cardinal.SOUTH)
            Direction.UP -> Point(x + distance, y, Cardinal.EAST)
            Direction.DOWN -> Point(x - distance, y, Cardinal.WEST)
        }
    }
}

fun Char.toDirection() = when (this) {
    'L' -> Direction.LEFT
    'R' -> Direction.RIGHT
    'U' -> Direction.UP
    'D' -> Direction.DOWN
    else -> throw Error("Invalid direction: $this")
}

const val INCLUDE_DIAGONAL = true
fun <T> List<T>.getAdjacents(point: Point<*>, boundary: Int, includeDiagonal: Boolean = false): List<T> {
    val (x, y) = point

    val diagonals = listOfNotNull(
        this.getAt(x - 1, y - 1, boundary),
        this.getAt(x + 1, y - 1, boundary),
        this.getAt(x - 1, y + 1, boundary),
        this.getAt(x + 1, y + 1, boundary),
        )
    val horizontalVerticals = listOfNotNull(
        this.getAt(x, y - 1, boundary),
        this.getAt(x - 1, y, boundary),
        this.getAt(x + 1, y, boundary),
        this.getAt(x, y + 1, boundary),
        )

    return if (includeDiagonal) {
        horizontalVerticals + diagonals
    } else {
        horizontalVerticals
    }
}

fun <T> List<T>.getAt(x: Int, y: Int, boundary: Int): T? {
    return if (x < 0 || x >= boundary || y < 0 || y >= boundary) {
        null
    } else {
        this[y * boundary + x]
    }
}