package fr.esgi.business;

/**
 * Enumeration décrivant le statut d'une Lettre selon les quatres états possibles pour celle-ci, indefini, absente du mot, présente mais au mauvais endroit, au bon endroit.
 */
public enum StatutLettre {
    /**
     * La valeur est VALIDE si le caractère est présent dans le mot à trouver mais n'est pas au bon endroit.
     */
    VALIDE,
    /**
     * La valeur est TROUVE si le caractère est présent dans le mot à trouver et est au bon endroit.
     */
    TROUVE,
    /**
     * La valeur est DEFAUT si aucun statut n'a encore été déterminé pour la Lettre.
     */
    DEFAUT,
    /**
     * La valeur est ABSENTE si le caractère n'est pas présent dans le mot à trouver.
     */
    ABSENTE
}
