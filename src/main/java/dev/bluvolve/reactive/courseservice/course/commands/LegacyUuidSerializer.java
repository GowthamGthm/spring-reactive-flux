package dev.bluvolve.reactive.courseservice.course.commands;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LegacyUuidSerializer extends StdSerializer<UUID> {
	
	private static final long serialVersionUID = 8770846509706527846L;

	public LegacyUuidSerializer() {
        super(UUID.class);
    }

    @Override
    public void serialize(UUID value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        byte[] bytes = new byte[16];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putLong(value.getMostSignificantBits());
        byteBuffer.putLong(value.getLeastSignificantBits());
        String base64EncodedUuid = Base64.getEncoder().encodeToString(bytes);
        generator.writeStartObject();
        generator.writeStringField("$binary", base64EncodedUuid);
        generator.writeStringField("$type", "03"); // Assume always type 3.
        generator.writeEndObject();
    }
}