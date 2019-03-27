CREATE OR REPLACE FUNCTION public.calculatesumandmediana(
	)
    RETURNS TABLE(sumint bigint, medianadouble double precision) 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

BEGIN
		RETURN QUERY 
		SELECT sum(intnumber), 
				percentile_disc(0.5) within group (order by doublenumber)
		FROM note;
END;

$BODY$;

ALTER FUNCTION public.calculatesumandmediana()
    OWNER TO postgres;