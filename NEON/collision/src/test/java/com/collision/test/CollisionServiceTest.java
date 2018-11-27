/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collision.test;

import com.collision.CollisionService;
import com.library.interfaces.Drawable;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;
import org.junit.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lagoni
 */
public class CollisionServiceTest {

    private CollisionService service;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new CollisionService();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void TestWithACollision() {
        //Mock the drawable interface, and ensure we can mock nested methods.
        Drawable drawMoc1 = mock(Drawable.class, Mockito.RETURNS_DEEP_STUBS);
        Vector2f position1 = new Vector2f(40, 40);
        when(drawMoc1.getSprite().getPosition()).thenReturn(position1);
        when(drawMoc1.getSprite().getWidth()).thenReturn(30f);

        List<Drawable> drawables = new ArrayList<>();
        drawables.add(drawMoc1);

        IWorldService worldMoc = mock(IWorldService.class);
        when(worldMoc.getEntities(Drawable.class)).thenReturn(drawables);
        service.setWorld(worldMoc);
        Vector2f position2 = new Vector2f(40, 40);
        List<Drawable> collisions = service.getCollisions(position2, 30f);

        assertEquals("Should find collision", 1, collisions.size());
    }

    @Test
    public void TestWithNoCollision() {
        //Mock the drawable interface, and ensure we can mock nested methods.
        Drawable drawMoc1 = mock(Drawable.class, Mockito.RETURNS_DEEP_STUBS);
        Vector2f position1 = new Vector2f(40, 40);
        when(drawMoc1.getSprite().getPosition()).thenReturn(position1);
        when(drawMoc1.getSprite().getWidth()).thenReturn(30f);

        List<Drawable> drawables = new ArrayList<>();
        drawables.add(drawMoc1);

        IWorldService worldMoc = mock(IWorldService.class);
        when(worldMoc.getEntities(Drawable.class)).thenReturn(drawables);
        service.setWorld(worldMoc);
        Vector2f position2 = new Vector2f(140, 140);
        List<Drawable> collisions = service.getCollisions(position2, 30f);

        assertTrue("Should not find collision", collisions.isEmpty());
    }
}
