package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Category;
@Local
public interface CategoriesService {
	
	public Category AddCategory( Category c);
	public List<Category> showCategory();
	public Boolean deleteCategory( int id );
	public Boolean updateCategory(int id);
}
