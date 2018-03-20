package treningsdagbok.core;

import java.sql.Connection;

public interface ActiveDomainObject {

    void save (Connection conn);
}