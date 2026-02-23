package com.pful.aitplacements

import org.junit.Assert.assertEquals
import org.junit.Test

class TrpcInputBuilderTest {

    @Test
    fun `createNoticeListInput builds page payload`() {
        assertEquals("{\"0\":{\"pageNos\":2}}", createNoticeListInput(2))
    }

    @Test
    fun `createNoticeDetailInput builds detail payload`() {
        assertEquals(
            "{\"0\":{\"id\":\"899a86e2-6046-4a06-a086-a75f2ec19285\"}}",
            createNoticeDetailInput("899a86e2-6046-4a06-a086-a75f2ec19285")
        )
    }

    @Test
    fun `createNoticePageInput builds batched page and detail payload`() {
        assertEquals(
            "{\"0\":{\"pageNos\":1},\"1\":{\"id\":\"9b28ffb9-b115-4557-ab6d-829187789f00\"}}",
            createNoticePageInput(1, "9b28ffb9-b115-4557-ab6d-829187789f00")
        )
    }
}
