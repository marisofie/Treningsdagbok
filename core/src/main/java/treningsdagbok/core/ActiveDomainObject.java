package treningsdagbok.core;

import java.sql.Connection;

public interface ActiveDomainObject {

    void getByID (Connection conn);
    void gettAll(Connection conn);
    void save (Connection conn);
}