package training.com.first.security;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;

public class PointToJsonSerializer  extends JsonSerializer<Point> {

	@Override
	public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		String jsonValue = "null";
        try
        {
            if(value != null) {             
                double lat = value.getY();
                double lon = value.getX();
                jsonValue = String.format("POINT (%s %s)", lon, lat);
                
            }
        }
        catch(Exception e) {}

        gen.writeString(jsonValue);
	}
}
