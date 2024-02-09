package org.redlich.beers;

import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfigurations;
import org.eclipse.jnosql.mapping.Convert;
import org.eclipse.jnosql.mapping.core.Converters;
import org.eclipse.jnosql.mapping.core.spi.EntityMetadataExtension;
import org.eclipse.jnosql.mapping.document.DocumentEntityConverter;
import org.eclipse.jnosql.mapping.document.spi.DocumentExtension;
import org.eclipse.jnosql.mapping.reflection.Reflections;
import org.eclipse.jnosql.mapping.validation.MappingValidator;
import org.jboss.weld.junit5.auto.AddExtensions;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;

@EnableAutoWeld
@AddPackages({
        MappingValidator.class,
        BeerApplication.class,
        Reflections.class,
        Converters.class,
        Convert.class,
        DocumentEntityConverter.class})
@AddExtensions({
        EntityMetadataExtension.class,
        DocumentExtension.class,
})
public abstract class BaseTest {

    static {
         System.setProperty(MongoDBDocumentConfigurations.HOST.get(), Database.INSTANCE.getConnectionString());
    }

}
