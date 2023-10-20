package stmall.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import stmall.domain.*;

@Component
public class ProductHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Product>> {

    @Override
    public EntityModel<Product> process(EntityModel<Product> model) {
        return model;
    }
}
