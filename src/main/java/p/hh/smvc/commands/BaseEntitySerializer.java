package p.hh.smvc.commands;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hibernate.proxy.HibernateProxy;
import p.hh.smvc.domain.BaseEntity;

import java.io.IOException;

/**
 * Created by Atos on 20-7-2017.
 */
public class BaseEntitySerializer extends StdSerializer<BaseEntity> {

    public BaseEntitySerializer() {
        this(null);
    }

    public BaseEntitySerializer(Class<BaseEntity> t) {
        super(t);
    }

    @Override
    public void serialize(BaseEntity baseEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        if(baseEntity instanceof HibernateProxy) {
            HibernateProxy proxy = (HibernateProxy) baseEntity;
            Long id = (Long) proxy.getHibernateLazyInitializer().getIdentifier();
            jsonGenerator.writeNumberField("id", id);
        } else {
            jsonGenerator.writeNumberField("id", baseEntity.getId());
        }
        jsonGenerator.writeEndObject();
    }
}
