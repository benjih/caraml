package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
	
	public static void search(String query, String responseType) {
		render();
	}

	public static void getArtists(String responseType) {
		render();
	}
	
    public static void getAlbums(String artist, String responseType) {
        render();
    }

}