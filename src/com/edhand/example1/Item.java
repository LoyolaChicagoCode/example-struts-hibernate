package com.edhand.example1;

import java.io.Serializable;

/**
 * Javabean used to store <code>Item</code> information.
 * 
 * Copyright 2003 Edward Hand
 * 
 * @author Edward Hand
 *
 */
/*
 * This class follows the Javabean pattern, holding three fields
 * and providing getters and setters for those fields.
 * 
 */
public class Item implements Serializable
{
	private String name;
	private String description;
	private int id;

    /**
     * getDescription() retrieves <code>description</code> field.
     * 
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * getName() retrieves <code>name</code> field.
     * 
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * getId() retrieves <code>id</code> field.
     * 
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * setDescription() sets <code>description</code> field.
     * 
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Sets <code>name</code> field.
     * 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    

    /**
     * Sets <code>id</code> field.
     * 
     */
    public void setId(int id)
    {
        this.id = id;
    }

}

