package guru.springframework.lombokbestpractices.entities;

/**
 * Created by jt, Spring Framework Guru.
 */
public enum BeerStyle {
    LAGER,
    PILSNER,
    STOUT,
    GOSE,
    PORTER,
    ALE,
    WHEAT,
    IPA,
    PALE_ALE,
    SAISON;

    private BeerStyle() {
    }
}
