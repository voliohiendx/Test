package volio.tech.sharefile.business.interactors

object InteractorEx {
    @Synchronized
    fun <T> removeFromList(list: MutableList<T>, item: T) {
        list.remove(item)
    }
}