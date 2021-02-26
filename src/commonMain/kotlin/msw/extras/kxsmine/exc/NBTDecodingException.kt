package msw.extras.kxsmine.exc

import msw.extras.kxsmine.toHexString
import msw.extras.kxsmine.tree.TagType

public class NBTDecodingException : NBTSerializationException {
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