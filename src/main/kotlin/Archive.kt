data class Archive(
    val name: String,
    val notes: MutableList<Note> = mutableListOf()
)

class ManagerArchive {
    private val archives = mutableListOf<Archive>()

    fun addArchive(name: String) {
        archives.add(Archive(name))
    }

    fun getArchive(): List<Archive> = archives

    fun getArchive(index: Int): Archive? {
        return if (index in archives.indices) archives[index] else null
    }
}
