/* infoScoop OpenSource
 * Copyright (C) 2010 Beacon IT Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0-standalone.html>.
 */

package org.infoscoop.dao.model.base;

import java.io.Serializable;


public abstract class BaseSiteaggregationmenuPK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.String type;
	private java.lang.String squareid;


	public BaseSiteaggregationmenuPK () {}
	
	public BaseSiteaggregationmenuPK ( java.lang.String id,java.lang.String squareid ) {

		this.setType(id);
		this.setSquareid(squareid);
	}


	public java.lang.String getType () {
		return type;
	}

	public void setType (java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getSquareid() {
		return squareid;
	}

	public void setSquareid(java.lang.String squareid) {
		this.squareid = squareid;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.infoscoop.dao.model.SiteaggregationmenuPK)) return false;
		else {
			org.infoscoop.dao.model.SiteaggregationmenuPK mObj = (org.infoscoop.dao.model.SiteaggregationmenuPK) obj;
			if (null != this.getType() && null != mObj.getType()) {
				if (!this.getType().equals(mObj.getType())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getSquareid() && null != mObj.getSquareid()) {
				if (!this.getSquareid().equals(mObj.getSquareid())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuffer sb = new StringBuffer();
			if (null != this.getType()) {
				sb.append(this.getType().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getSquareid()) {
				sb.append(this.getSquareid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}