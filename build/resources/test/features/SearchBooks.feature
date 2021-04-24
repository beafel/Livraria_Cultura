Feature: Login and Search for some Book at Livraria Cultura

  Scenario: Success Login
    Given I access Livraria Cultura homepage "1"
    When I do successfull login
    Then I see my name logged in

  Scenario Outline: Search for Books with Success
    Given I am at Livraria Cultura homepage <id>
    When I type the <title> of the book and click Return
    Then I check the <title> and the <author> of the book
    And I click on add to cart button
    When I click on cart button
    Then show the title of the <book> and price <value>
    Examples:
    | id  |         title          |               book                                   |     author     |    value    |
    | "2" |       "Dilbert 1"      | "DILBERT 1 – CORRA, O CONTROLE DE QUALIDADE VEM AÍ!" | "SCOTT ADAMS"  | "R$ 19,90"  |
    | "3" |       "Dilbert 4"      | "DILBERT 4 – TRABALHANDO EM CASA!"                   | "SCOTT ADAMS"  | "R$ 19,90"  |
    | "4" | "Use A Cabeca! - Java" | "USE A CABEÇA! JAVA"                                 | "KATHY SIERRA" | "R$ 172,00" |