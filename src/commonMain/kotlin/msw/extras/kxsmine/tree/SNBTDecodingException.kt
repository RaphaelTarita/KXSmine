package msw.extras.kxsmine.tree

import msw.extras.kxsmine.truncate

public class SNBTDecodingException : Exception {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)

    public constructor(type: TagType, faultyString: String)
            : super("Decoding of $type failed for string \"${faultyString.truncate(50)}\"")
}