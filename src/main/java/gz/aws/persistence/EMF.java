// Copyright 2008 Google Inc. All Rights Reserved.
package gz.aws.persistence;

import gz.aws.ioc.IocUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Max Ross <maxr@google.com>
 */
public final class EMF {
    private static final EntityManagerFactory INSTANCE;

    static {
        EntityManagerFactory ef = null;
        try {
            ef = (EntityManagerFactory) IocUtil.get("entityManagerFactory");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (ef == null) {
            ef = Persistence.createEntityManagerFactory("blog");
        }
        INSTANCE = ef;
    }

    public static EntityManagerFactory get() {
        return INSTANCE;
    }

    private EMF() {
    }
}