/*
 * Copyright (c) 2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sbolstandard.core;

/**
 * Validator interface to validate the contents of SBOL documents. The contents of SBOL documents are checked against
 * the SBOL schema as well as the additional constraints described in the SBOL specification.
 * 
 * @author Evren Sirin
 */
public interface SBOLValidator {
	/**
	 * Validates the contents of the document and throws a {@link SBOLValidationException} if validation fails. The 
	 * function returns without an exception if the validation is successful.
	 * 
	 * @param doc
	 * @throws SBOLValidationException
	 */
	public void validate(SBOLDocument doc) throws SBOLValidationException;
}