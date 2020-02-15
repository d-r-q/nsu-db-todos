package todos.usecases

interface TView {

    fun show()

    fun onEvent(e: TEvent)

}

interface TEvent
