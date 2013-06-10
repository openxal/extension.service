//
//  ImageEntryTableModel.java
//  xal
//
//  Created by Thomas Pelaia on 9/21/06.
//  Copyright 2006 Oak Ridge National Lab. All rights reserved.
//

package xal.app.labbook;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


/** table model for image entries */
public class ImageEntryTableModel extends AbstractTableModel {
    /** ID for serializable version */
    private static final long serialVersionUID = 1L;
	/** image file name column */
	final static public int FILE_NAME_COLUMN = 0;
	
	/** image entry title column */
	final static public int TITLE_COLUMN = FILE_NAME_COLUMN + 1;
	
	/** list of files to display in the table */
	protected List<ImageEntry> _imageEntries;
	
	
	/** Primary Constructor */
	public ImageEntryTableModel( final List<ImageEntry> entries ) {
		setImageEntries( entries );
	}
	
	
	/** Constructor */
	public ImageEntryTableModel() {
		this( new ArrayList<ImageEntry>() );
	}
	
	
	/** get the image entry at the specified row */
	public ImageEntry getImageEntry( final int row ) {
		final List<ImageEntry> entries = _imageEntries;
		return entries.size() > row ? entries.get( row ) : null;
	}
	
	
	/** get the image entries */
	public List<ImageEntry> getImageEntries() {
		return _imageEntries;
	}
	
	
	/** set the list of image entries to display */
	public void setImageEntries( final List<ImageEntry> entries ) {
		_imageEntries = entries;
		refresh();
	}
	
	
	/** refresh the table model */
	public void refresh() {
		fireTableDataChanged();
	}
	
	
	/** get the column title */
	public String getColumnName( final int column ) {
		switch( column ) {
			case FILE_NAME_COLUMN:
				return "File Name";
			case TITLE_COLUMN:
				return "Title";
			default:
				return null;
		}
	}
	
	
	/** determine whether the specified cell is editable */
	public boolean isCellEditable( final int row, final int column ) {
		switch( column ) {
			case FILE_NAME_COLUMN:
				return false;
			case TITLE_COLUMN:
				return true;
			default:
				return false;
		}
	}
	
	
	/** get the row count */
	public int getRowCount() {
		final List<ImageEntry> entries = _imageEntries;
		return entries.size();
	}
	
	
	/** get the column count */
	public int getColumnCount() {
		return 2;
	}
	
	
	/** get the value for the specified cell */
	public Object getValueAt( final int row, final int column ) {
		final List<ImageEntry> entries = _imageEntries;
		
		if ( row >= entries.size() )  return null;
		
		final ImageEntry entry = entries.get( row );
		
		switch( column ) {
			case FILE_NAME_COLUMN:
				return entry.getImageFile().getName();
			case TITLE_COLUMN:
				return entry.getTitle();
			default:
				return null;
		}
	}
	
	
	/** set the value for the specified cell */
	public void setValueAt( final Object value, final int row, final int column ) {
		final List<ImageEntry> entries = _imageEntries;
		
		if ( row >= entries.size() )  return;
		
		final ImageEntry entry = entries.get( row );
		
		switch( column ) {
			case TITLE_COLUMN:
				entry.setTitle( value.toString() );
				break;
			default:
				break;
		}
	}
}

