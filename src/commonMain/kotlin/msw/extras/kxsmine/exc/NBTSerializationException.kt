package msw.extras.kxsmine.exc

import kotlinx.serialization.SerializationException

public open class NBTSerializationException : SerializationException {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)
}