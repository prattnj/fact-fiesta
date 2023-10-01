package main

import (
	"github.com/labstack/echo/v4"
)

func main() {

	e := echo.New()

	port := "3004"

	e.GET("/", HandleGetFact)

	e.Logger.Fatal(e.Start(port))
}
