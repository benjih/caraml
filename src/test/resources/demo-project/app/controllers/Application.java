package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import com.benjih.caraml.annotations.CaramlController;

import models.*;

public class Application extends Controller {
	
	public static void search(String query, String responseType) {
		render();
	}

	@CaramlController("This is look up for every artist available on the service.")
	public static void getArtists(String responseType) {
		render();
	}
	
	@CaramlController("This will provide the albums for a given artist.")
    public static void getAlbums(String artist, String responseType) {
        render();
    }

}