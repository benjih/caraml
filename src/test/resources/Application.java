package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	@CaramlController("This is about showArtists")
    public static void showArtists() {
        render();
    }
    
    public static void showAlbums(String artist) {
    	render();
    }
    
}