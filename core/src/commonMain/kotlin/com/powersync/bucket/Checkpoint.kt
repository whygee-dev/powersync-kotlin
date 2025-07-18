package com.powersync.bucket

import com.powersync.sync.LegacySyncImplementation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@LegacySyncImplementation
@Serializable
internal data class Checkpoint(
    @SerialName("last_op_id") val lastOpId: String,
    @SerialName("buckets") val checksums: List<BucketChecksum>,
    @SerialName("write_checkpoint") val writeCheckpoint: String? = null,
) {
    fun clone(): Checkpoint = Checkpoint(lastOpId, checksums, writeCheckpoint)
}
