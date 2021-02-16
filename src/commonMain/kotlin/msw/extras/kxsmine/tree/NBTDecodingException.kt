package msw.extras.kxsmine.tree

import msw.extras.kxsmine.toHexString

public class NBTDecodingException : Exception {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(message: String?, cause: Throwable?) : super(message, cause)
    public constructor(cause: Throwable?) : super(cause)

    public constructor(type: TagType, bytes: ByteArray, offset: Int = 0)
            : super(
        "Decoding of $type failed for bytes \'${
            bytes.toHexString(offset)
                .chunked(2)
                .joinToString(" ", limit = 50)
        }\'"
    )
}