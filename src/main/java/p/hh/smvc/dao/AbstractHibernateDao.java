package p.hh.smvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

abstract class AbstractHibernateDao< T extends Serializable> {

    private final Class< T > clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractHibernateDao() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById( long id ){
        return (T) getSession().get( clazz, id );
    }

    public List< T > findAll(){
        return (List<T>) createEntityCriteria().list();
    }

    public void create( T entity ){
        getSession().persist( entity );
    }

    public void update( T entity ){
        getSession().merge( entity );
    }

    public void delete( T entity ){
        getSession().delete( entity );
    }
    public void deleteById( long id ) {
        T entity = findById( id );
        delete( entity );
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(clazz);
    }

}
