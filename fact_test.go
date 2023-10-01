package main

import (
	"fmt"
	"testing"
)

func TestGetFact(t *testing.T) {
	fact := getFact()
	fmt.Println(fact)
}
