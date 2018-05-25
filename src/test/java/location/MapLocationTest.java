package location;

import location.object.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MapLocationTest {

    private MapLocation mapLocation;

    private MapObject personMapObject;

    @Before
    public void setUp() throws Exception {
        personMapObject = new PersonMapObject(0, 0);

        MapObject creature1 = new CreatureMapObject(2, 2);
        MapObject creature2 = new CreatureMapObject(2, 0);

        CompositeMapObject creatures = new CompositeMapObject();
        creatures.addObject(creature1);
        creatures.addObject(creature2);

        MapObject treasure = new TreasureMapObject(2, 1);

        CompositeMapObject treasures = new CompositeMapObject();
        treasures.addObject(treasure);

        CompositeMapObject allObjects = new CompositeMapObject();
        allObjects.addObject(personMapObject);
        allObjects.addObject(creatures);
        allObjects.addObject(treasure);

        mapLocation = new MapLocation(3, allObjects);
    }

    @Test
    public void testMapShouldExist() {
        assertNotNull(mapLocation.getMap());
    }

    @Test
    public void testMapWidthShouldBeConfigurable() {
        assertEquals(3, mapLocation.getMap().length);
    }

    @Test
    public void testMapHeightShouldBeConfigurable() {
        assertEquals(3, mapLocation.getMap()[0].length);
        assertEquals(3, mapLocation.getMap()[1].length);
        assertEquals(3, mapLocation.getMap()[2].length);
    }

    @Test
    public void testMapShouldBeSquare() {
        assertEquals(mapLocation.getMap()[0].length, mapLocation.getMap().length);
    }

    @Test
    public void testMapShouldBeFilled() {
        for (MapFieldType[] array : mapLocation.getMap()) {
            for (MapFieldType type : array) {
                assertNotNull(type);
            }
        }
    }

    @Test
    public void testMapShouldBePopulatedByOnePerson() {
        int count = 0;
        mapLocation.populate();
        for (MapFieldType[] array : mapLocation.getMap()) {
            for (MapFieldType type : array) {
                if (MapFieldType.PERSON.equals(type)) count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    public void testMapShouldBePopulatedByCreatures() {
        int count = 0;
        mapLocation.populate();
        for (MapFieldType[] array : mapLocation.getMap()) {
            for (MapFieldType type : array) {
                if (MapFieldType.CREATURE.equals(type)) count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testMapShouldBePopulatedByTreasures() {
        int count = 0;
        mapLocation.populate();
        for (MapFieldType[] array : mapLocation.getMap()) {
            for (MapFieldType type : array) {
                if (MapFieldType.TREASURE.equals(type)) count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    public void testPersonShouldGo4Directions() {
        int size = mapLocation.getMap().length;
        assertEquals(0, personMapObject.getX());
        assertEquals(0, personMapObject.getY());
        personMapObject.goEast(size);
        assertEquals(1, personMapObject.getX());
        assertEquals(0, personMapObject.getY());
        personMapObject.goSouth(size);
        assertEquals(1, personMapObject.getX());
        assertEquals(1, personMapObject.getY());
        personMapObject.goWest();
        assertEquals(0, personMapObject.getX());
        assertEquals(1, personMapObject.getY());
        personMapObject.goNorth();
        assertEquals(0, personMapObject.getX());
        assertEquals(0, personMapObject.getY());
    }

    @Test
    public void testPersonShouldNotGoOutTheMap() {
        int size = mapLocation.getMap().length;
        personMapObject.goNorth();
        assertEquals(0, personMapObject.getX());
        assertEquals(0, personMapObject.getY());
        for (int i = 0; i < size + 2; i++) {
            personMapObject.goEast(size);
        }
        assertEquals(size, personMapObject.getX());
        assertEquals(0, personMapObject.getY());
        for (int i = 0; i < size + 2; i++) {
            personMapObject.goSouth(size);
        }
        assertEquals(size, personMapObject.getX());
        assertEquals(size, personMapObject.getY());
        for (int i = 0; i < size + 2; i++) {
            personMapObject.goWest();
        }
        assertEquals(0, personMapObject.getX());
        assertEquals(size, personMapObject.getY());
    }

    @Test
    public void testPreviousPlaceShouldBeEmpty() {
        int size = mapLocation.getMap().length;
        mapLocation.populate();
        personMapObject.goEast(size);
        mapLocation.populate();
        assertEquals(MapFieldType.EMPTY, mapLocation.getMap()[0][0]);
    }

    @Test
    public void testMapObjectShouldBeRemovedWhenPersonStepsInto() {
        int size = mapLocation.getMap().length;
        mapLocation.populate();
        assertEquals(MapFieldType.CREATURE, mapLocation.getMap()[0][2]);
        personMapObject.goEast(size);
        mapLocation.populate();
        personMapObject.goEast(size);
        mapLocation.isCellBusy(personMapObject.getX(), personMapObject.getY());
        mapLocation.populate();
        personMapObject.goWest();
        mapLocation.populate();
        assertEquals(MapFieldType.EMPTY, mapLocation.getMap()[0][2]);
    }
}
