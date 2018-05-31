// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.phoenixctms.ctsms.enumeration.AuthenticationType;
import org.phoenixctms.ctsms.enumeration.VariablePeriod;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.query.SubCriteriaMap;
import org.phoenixctms.ctsms.util.L10nUtil;
import org.phoenixctms.ctsms.util.L10nUtil.Locales;
import org.phoenixctms.ctsms.util.ServiceUtil;
import org.phoenixctms.ctsms.vo.PSFVO;
import org.phoenixctms.ctsms.vo.PasswordInVO;
import org.phoenixctms.ctsms.vo.PasswordOutVO;

/**
 * @see Password
 */
public class PasswordDaoImpl
extends PasswordDaoBase
{

	private org.hibernate.Criteria createPasswordCriteria(String alias) {
		org.hibernate.Criteria passwordCriteria;
		if (alias != null && alias.length() > 0) {
			passwordCriteria = this.getSession().createCriteria(Password.class, alias);
		} else {
			passwordCriteria = this.getSession().createCriteria(Password.class);
		}
		return passwordCriteria;
	}

	@Override
	protected Collection<Password> handleFindExpiring(Date today,
			Long departmentId, AuthenticationType authMethod, VariablePeriod reminderPeriod,
			Long reminderPeriodDays, Boolean notify, boolean includeAlreadyPassed, PSFVO psf) throws Exception {
		org.hibernate.Criteria passwordCriteria = createPasswordCriteria("password0");
		SubCriteriaMap criteriaMap = new SubCriteriaMap(Password.class, passwordCriteria);
		if (departmentId != null) {
			criteriaMap.createCriteria("user").add(Restrictions.eq("department.id", departmentId.longValue()));
		}
		if (authMethod != null) {
			criteriaMap.createCriteria("user").add(Restrictions.eq("authMethod", authMethod));
		}
		DetachedCriteria subQuery = DetachedCriteria.forClass(PasswordImpl.class, "password1"); // IMPL!!!!
		subQuery.add(Restrictions.eqProperty("password1.user", "password0.user"));
		subQuery.setProjection(Projections.max("id"));
		passwordCriteria.add(Subqueries.propertyEq("id", subQuery));
		passwordCriteria.add(Restrictions.eq("expires", true)); // performance only...
		if (notify != null) {
			passwordCriteria.add(Restrictions.eq("prolongable", notify.booleanValue())); // performance only...
		}
		if (psf != null) {
			PSFVO sorterFilter = new PSFVO();
			sorterFilter.setFilters(psf.getFilters());
			sorterFilter.setSortField(psf.getSortField());
			sorterFilter.setSortOrder(psf.getSortOrder());
			CriteriaUtil.applyPSFVO(criteriaMap, sorterFilter);
		}
		ArrayList<Password> resultSet = CriteriaUtil.listExpirations(passwordCriteria, today, notify, includeAlreadyPassed, null, null, reminderPeriod, reminderPeriodDays);
		return (Collection<Password>) CriteriaUtil.applyPVO(resultSet, psf, false); // no dupes by default
	}

	/**
	 * @inheritDoc
	 */
	@Override
	protected Password handleFindLastPassword(Long userId) {
		org.hibernate.Criteria passwordCriteria = createPasswordCriteria(null);
		if (userId != null) {
			passwordCriteria.add(Restrictions.eq("user.id", userId.longValue()));
		}
		passwordCriteria.addOrder(Order.desc("id"));
		passwordCriteria.setMaxResults(1);
		return (Password) passwordCriteria.uniqueResult();
	}

	@Override
	protected long handleGetCount(Long userId) throws Exception {
		org.hibernate.Criteria passwordCriteria = createPasswordCriteria(null);
		passwordCriteria.add(Restrictions.eq("user.id", userId.longValue()));
		return (Long) passwordCriteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private Password loadPasswordFromPasswordInVO(PasswordInVO passwordInVO)
	{
		// TODO implement loadPasswordFromPasswordInVO
		throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadPasswordFromPasswordInVO(PasswordInVO) not yet implemented.");
		/* A typical implementation looks like this: Password password = this.load(passwordInVO.getId()); if (password == null) { password = Password.Factory.newInstance(); }
		 * return password; */
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private Password loadPasswordFromPasswordOutVO(PasswordOutVO passwordOutVO)
	{
		// TODO implement loadPasswordFromPasswordOutVO
		throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadPasswordFromPasswordOutVO(PasswordOutVO) not yet implemented.");
		/* A typical implementation looks like this: Password password = this.load(passwordOutVO.getId()); if (password == null) { password = Password.Factory.newInstance(); }
		 * return password; */
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Password passwordInVOToEntity(PasswordInVO passwordInVO)
	{
		// TODO verify behavior of passwordInVOToEntity
		Password entity = Password.Factory.newInstance();
		this.passwordInVOToEntity(passwordInVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void passwordInVOToEntity(
			PasswordInVO source,
			Password target,
			boolean copyIfNull)
	{
		// TODO verify behavior of passwordInVOToEntity
		super.passwordInVOToEntity(source, target, copyIfNull);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Password passwordOutVOToEntity(PasswordOutVO passwordOutVO)
	{
		// TODO verify behavior of passwordOutVOToEntity
		Password entity = this.loadPasswordFromPasswordOutVO(passwordOutVO);
		this.passwordOutVOToEntity(passwordOutVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void passwordOutVOToEntity(
			PasswordOutVO source,
			Password target,
			boolean copyIfNull)
	{
		// TODO verify behavior of passwordOutVOToEntity
		super.passwordOutVOToEntity(source, target, copyIfNull);
		// No conversion for target.validityPeriod (can't convert source.getValidityPeriod():org.phoenixctms.ctsms.vo.VariablePeriodVO to org.phoenixctms.ctsms.enumeration.VariablePeriod
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PasswordInVO toPasswordInVO(final Password entity)
	{
		// TODO verify behavior of toPasswordInVO
		return super.toPasswordInVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toPasswordInVO(
			Password source,
			PasswordInVO target)
	{
		// TODO verify behavior of toPasswordInVO
		super.toPasswordInVO(source, target);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public PasswordOutVO toPasswordOutVO(final Password entity)
	{
		// TODO verify behavior of toPasswordOutVO
		return super.toPasswordOutVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toPasswordOutVO(
			Password source,
			PasswordOutVO target)
	{
		// TODO verify behavior of toPasswordOutVO
		super.toPasswordOutVO(source, target);
		// WARNING! No conversion for target.validityPeriod (can't convert source.getValidityPeriod():org.phoenixctms.ctsms.enumeration.VariablePeriod to
		// org.phoenixctms.ctsms.vo.VariablePeriodVO
		// WARNING! No conversion for target.user (can't convert source.getUser():org.phoenixctms.ctsms.domain.User to org.phoenixctms.ctsms.vo.UserOutVO
		User user = source.getUser();
		if (user != null) {
			target.setUser(this.getUserDao().toUserOutVO(user)); // ensure identity available for "logon"
		}
		target.setValidityPeriod(L10nUtil.createVariablePeriodVO(Locales.USER, source.getValidityPeriod()));
		if (target.isExpires()) {
			target.setExpiration(ServiceUtil.getLogonExpirationDate(source));
		}
	}
}