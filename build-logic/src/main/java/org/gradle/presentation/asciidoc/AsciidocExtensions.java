package org.gradle.presentation.asciidoc;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry ;

public class AsciidocExtensions implements ExtensionRegistry {
    @Override
    public void register(Asciidoctor asciidoctor) {
        asciidoctor
                .javaExtensionRegistry()
                .inlineMacro(new ScreencastAsciidoctorExtension());
    }
}