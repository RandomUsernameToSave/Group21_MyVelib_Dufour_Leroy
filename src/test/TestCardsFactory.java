	@Test
	public void testPriceVlibreElectricalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(1, result);
	}
	@Test
	public void testPriceVlibreElectricalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(5, result);
	}
	@Test
	public void testPriceVlibreMechanicalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(0, result);
	}
	@Test
	public void testPriceVlibreMechanicalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(2, result);
	}
