package org.openbase.bco.registry.editor.visual.cell;

/*
 * #%L
 * BCO Registry Editor
 * %%
 * Copyright (C) 2014 - 2018 openbase.org
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import org.openbase.bco.registry.editor.struct.NodeInterface;

/**
 *
 * @author <a href="mailto:pleminoq@openbase.org">Tamino Huxohl</a>
 */
public class DescriptionCell extends RowCell {

    public DescriptionCell() throws InterruptedException {
        super();
    }

    @Override
    public void updateItem(NodeInterface item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            graphicProperty().setValue(null);
            textProperty().setValue("");
            setContextMenu(null);
        } else {
            setText(item.getDisplayedDescriptor());
        }
    }
}
