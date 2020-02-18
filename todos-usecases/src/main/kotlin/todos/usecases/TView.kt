package todos.usecases

interface TView<E : TEvent> {

    fun show()

    fun onEvent(e: E)

}

interface TEvent
