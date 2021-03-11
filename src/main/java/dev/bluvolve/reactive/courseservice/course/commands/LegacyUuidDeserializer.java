package dev.bluvolve.reactive.courseservice.course.commands;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import dev.bluvolve.reactive.courseservice.course.Course;

public class LegacyUuidDeserializer extends StdDeserializer<CreateCourse> {
	
	private static final long serialVersionUID = -8818416218910108403L;

	public LegacyUuidDeserializer() {
        super(UUID.class);
    }

    @Override
    public CreateCourse deserialize(JsonParser parser, DeserializationContext context) throws IOException {

		JsonNode node = parser.getCodec().readTree(parser);
		UUID categoryId = UUID.fromString(node.get("categoryId").asText(""));
		UUID userId = UUID.fromString(node.get("createdByUserId").asText(""));

		CreateCourse course = new CreateCourse(node.get("title").asText(), node.get("description").asText(), categoryId,
				userId, node.get("duration").asLong(45));
		return course;
        
    }
    
    
}