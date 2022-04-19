Feature: Login and Search for some Books at Livraria Cultura

  Scenario: Success Login
    Given I access Livraria Cultura homepage "1"
    When I do successfull login
    Then I see my name logged in

  Scenario Outline: Search for Books with Success
    Given I am at Livraria Cultura homepage <id>
    When I type the <title> of the book and click Return
    Then I check the search title the <title> the <book> and the <author>
    When I click on add to cart button
    Then show the title of the <book> and subtotal <value>
    Examples:
    | id  |         title                       |               book               |     author     |   value    |
    | "2" |       "Dilbert 5"                   | "DILBERT 5 – ODEIO REUNIÕES!"    | "SCOTT ADAMS"  | "R$ 19,90" |
    | "3" |       "Dilbert 8"                   | "DILBERT 8 - PAUSA PARA O CAFÉ" | "SCOTT ADAMS"  | "R$ 19,90" |
    # ESGOTADO | "4" | "HEAD FIRST JAVA - Java Kathy Sierra" | "HEAD FIRST JAVA"                  | "KATHY SIERRA" | "R$ 66,99" |