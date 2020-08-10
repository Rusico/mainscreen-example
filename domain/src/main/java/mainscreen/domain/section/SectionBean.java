package mainscreen.domain.section;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;

@Inherited
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface SectionBean {
}
