/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2011, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */

/*
* generated by Xtext
*/
package org.openhab.model.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemRegistry;
import org.openhab.designer.ui.UIActivator;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class SitemapProposalProvider extends AbstractSitemapProposalProvider {
	
	@Override
	public void completeGroup_Item(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeGroup_Item(model, assignment, context, acceptor);

		ItemRegistry registry = (ItemRegistry) UIActivator.itemRegistryTracker.getService();
		if(registry!=null) {
			for(Item item : registry.getItems(context.getPrefix() + "*")) {
				if(item instanceof GroupItem) {
					ICompletionProposal completionProposal = createCompletionProposal(item.getName(), context);
					acceptor.accept(completionProposal);
				}
			}
			context.setPrefix("");
		}
	}
	
	@Override
	public void completeSwitch_Item(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeSwitch_Item(model, assignment, context, acceptor);

		doComplete(context, acceptor);
	}

	@Override
	public void completeText_Item(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		super.completeSwitch_Item(model, assignment, context, acceptor);

		doComplete(context, acceptor);
	}

	public void doComplete(ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		ItemRegistry registry = (ItemRegistry) UIActivator.itemRegistryTracker.getService();
		if(registry!=null) {
			for(Item item : registry.getItems(context.getPrefix() + "*")) {
				ICompletionProposal completionProposal = createCompletionProposal(item.getName(), context);
				acceptor.accept(completionProposal);
			}
			context.setPrefix("");
		}
	}
}
